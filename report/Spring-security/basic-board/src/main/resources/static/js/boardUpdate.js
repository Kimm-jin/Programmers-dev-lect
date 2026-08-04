let selectedFile = null;

$(document).ready(() => {
    if (!checkToken()) {
        return;
    }

    loadBoardDetail();
    updated();
    fileChanged();
});

let updated = () => {
    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        const hId = $('#hiddenId').val();
        const formData = new FormData($('#writeForm')[0]);

        authAjax({
            type: 'PUT',
            url: '/api/boards/' + hId,
            data: formData,
            processData: false,
            contentType: false,

            success: function () {
                alert('게시글이 성공적으로 수정되었습니다.');
                window.location.href = '/';
            },

            error: function (error) {
                handleRequestError(
                    error,
                    '게시글 수정 중 오류가 발생했습니다.'
                );
            }
        });
    });
};

let fileChanged = () => {
    $('#file').on('change', function (event) {
        const file = event.target.files[0];

        if (!file) {
            return;
        }

        selectedFile = file;

        $('#hiddenFileFlag').val(true);

        updateFileList();
    });
};

let updateFileList = () => {
    $('#fileList').empty();

    if (!selectedFile) {
        $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
        return;
    }

    $('#fileList').append(`
        <li>
            ${selectedFile.name}
            <button type="button" class="remove-btn">X</button>
        </li>
    `);

    $('.remove-btn').on('click', function () {
        selectedFile = null;
        $('#file').val('');

        $('#hiddenFileFlag').val(false);

        updateFileList();
    });
};

let loadBoardDetail = () => {
    const hId = $('#hiddenId').val();

    authAjax({
        type: 'GET',
        url: '/api/boards/' + hId,

        success: (response) => {
            $('#title').val(response.title);
            $('#content').val(response.content);
            $('#userId').val(response.userId);

            renderExistingFile(response.filePath);
        },

        error: function (error) {
            handleRequestError(
                error,
                '상세 데이터를 불러오는데 오류가 발생했습니다.'
            );
        }
    });
};

let renderExistingFile = (filePath) => {
    $('#fileList').empty();

    if (!filePath) {
        $('#hiddenFilePath').val('');
        $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
        return;
    }

    $('#hiddenFilePath').val(filePath);

    const normalizedPath = filePath.replace(/\\/g, '/');
    const fileName =
        normalizedPath.substring(normalizedPath.lastIndexOf('/') + 1);

    $('#fileList').append(`
        <li>
            ${fileName}
            <button type="button" class="remove-btn">X</button>
        </li>
    `);


    $('.remove-btn').on('click', function () {
        selectedFile = null;

        $('#file').val('');
        $('#hiddenFileFlag').val(true);
        $('#fileList').empty();
        $('#fileList').append('<li>첨부된 파일이 없습니다.</li>');
    });
};


