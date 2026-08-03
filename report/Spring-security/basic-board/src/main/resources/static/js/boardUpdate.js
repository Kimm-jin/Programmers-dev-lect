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

        $.ajax({
            type: 'PUT',
            url: '/api/boards/' + hId,
            data: formData,
            processData: false,
            contentType: false,

            headers: {
                Authorization:
                    `Bearer ${localStorage.getItem('accessToken')}`
            },

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

// Access Token 존재 여부 확인
let checkToken = () => {
    const accessToken = localStorage.getItem('accessToken');

    if (!accessToken) {
        window.location.href = '/members/login';
        return false;
    }

    return true;
};

let loadBoardDetail = () => {
    const hId = $('#hiddenId').val();

    $.ajax({
        type: 'GET',
        url: '/api/boards/' + hId,

        headers: {
            Authorization:
                `Bearer ${localStorage.getItem('accessToken')}`
        },

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


let handleRequestError = (error, defaultMessage) => {
    console.error('요청 오류:', error);

    if (error.status === 401) {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');

        alert('로그인이 필요하거나 토큰이 만료되었습니다.');
        window.location.href = '/members/login';
        return;
    }

    if (error.status === 403) {
        alert('게시글을 수정할 권한이 없습니다.');
        window.location.href = '/';
        return;
    }

    alert(defaultMessage);
};
