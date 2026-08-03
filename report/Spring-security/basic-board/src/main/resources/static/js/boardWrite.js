let selectedFile = null; // 파일은 1개만 선택 가능

$(document).ready(() => {
    if (!checkToken()) {
        return;
    }

    loadMemberInfo();
    saved();
    fileChaged();
});

let saved = () => {
    $('#submitBtn').on('click', (event) => {
        event.preventDefault();

        let formData = new FormData($('#writeForm')[0]);

        $.ajax({
            type: 'POST',
            url: '/api/boards',
            data: formData,
            processData: false,
            contentType: false,

            headers: {
                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
            },

            success: function(response) {
                alert('게시글이 성공적으로 등록되었습니다!');
                window.location.href = '/';
            },

            error: function(error) {
                console.error('오류 발생:', error);

                if (error.status === 401) {
                    localStorage.removeItem('accessToken');
                    localStorage.removeItem('refreshToken');

                    alert('로그인이 필요합니다.');
                    window.location.href = '/members/login';
                    return;
                }

                alert('게시글 등록 중 오류가 발생하였습니다.');
            }
        });

    });
}

let fileChaged = () => {
    // 파일 선택 시 이벤트
    $('#file').on('change', function(e) {
        const file = e.target.files[0]; // 첫 번째 파일만 선택

        selectedFile = file; // 선택된 파일을 변수에 저장
        updateFileList(); // 파일 목록 업데이트
    });
}

// 파일 목록 업데이트 함수 (파일 하나만)
let updateFileList = () => {
    $('#fileList').empty(); // 기존 목록 비우기

    if (selectedFile) {
        $('#fileList').append(`
                    <li>
                        ${selectedFile.name} <button type="button" class="remove-btn">X</button>
                    </li>
                `);

        // X 버튼 클릭 시 파일 제거
        $('.remove-btn').on('click', function () {
            selectedFile = null; // 선택된 파일 제거
            $('#file').val(''); // 파일 input 초기화
            updateFileList(); // 파일 목록 갱신
        });
    }
}

let checkToken = () => {
    const accessToken = localStorage.getItem('accessToken');

    if (!accessToken) {
        window.location.href = '/members/login';
        return false;
    }

    return true;
};

let loadMemberInfo = () => {
    $.ajax({
        type: 'GET',
        url: '/api/members/info',

        headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },

        success: (member) => {
            $('#userName').val(member.userName);
        },

        error: (error) => {
            console.error('사용자 정보 조회 실패:', error);

            if (error.status === 401) {
                localStorage.removeItem('accessToken');
                localStorage.removeItem('refreshToken');
                window.location.href = '/members/login';
                return;
            }

            alert('작성자 정보를 불러오지 못했습니다.');
        }
    });
};
