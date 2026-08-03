$(document).ready(() => {
    if (!checkToken()) {
        return;
    }

    loadBoardDetail();
});

let editArticle = () => {
    const resourceId = $('#hiddenId').val();
    window.location.href = '/update/' + resourceId;
};

let deleteArticle = () => {
    const resourceId = $('#hiddenId').val();
    const filePath = $('#hiddenFilePath').val();

    $.ajax({
        type: 'DELETE',
        url: '/api/boards/' + resourceId,

        headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },

        data: JSON.stringify({
            filePath: filePath
        }),

        contentType: 'application/json',

        success: () => {
            alert('게시글이 삭제되었습니다.');
            window.location.href = '/';
        },

        error: (error) => {
            handleRequestError(
                error,
                '게시글 삭제 중 오류가 발생했습니다.'
            );
        }
    });
};

let checkToken = () => {
    const accessToken = localStorage.getItem('accessToken');

    if (!accessToken) {
        window.location.href = '/members/login';
        return false;
    }

    return true;
};

let loadBoardDetail = () => {
    const boardId = $('#hiddenId').val();

    $.ajax({
        type: 'GET',
        url: '/api/boards/' + boardId + '/with-comments',

        headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },

        success: (response) => {
            $('#title').text(response.title);
            $('#content').text(response.content);
            $('#userId').text(response.userId);
            $('#created').text(response.created);

            checkBoardOwner(response.userId);
            renderFile(response.filePath);
            renderComments(response.comments);
        },

        error: (error) => {
            handleRequestError(
                error,
                '상세 데이터를 불러오는데 오류가 발생했습니다.'
            );
        }
    });
};

let renderFile = (filePath) => {
    $('#fileList').empty();

    if (!filePath) {
        $('#hiddenFilePath').val('');
        $('#fileList').append(
            '<li>첨부된 파일이 없습니다.</li>'
        );
        return;
    }

    $('#hiddenFilePath').val(filePath);

    const normalizedPath = filePath.replace(/\\/g, '/');
    const fileName =
        normalizedPath.substring(
            normalizedPath.lastIndexOf('/') + 1
        );

    $('#fileList').append(`
        <li>
            <a href="/api/boards/file/download/${fileName}">
                ${fileName}
            </a>
        </li>
    `);
};

let renderComments = (comments) => {
    const $list = $('#commentList');

    $list.empty();

    const commentCount =
        comments && comments.length > 0
            ? comments.length
            : '';

    $('#commentCount').text(commentCount);

    if (!comments || comments.length === 0) {
        $list.append(`
            <li class="no-comment">
                아직 댓글이 없습니다. 첫 댓글을 남겨보세요!
            </li>
        `);
        return;
    }

    comments.forEach((comment) => {
        $list.append(`
            <li class="comment-item">
                <div class="comment-meta">
                    <strong>${comment.userId}</strong>
                    <span class="comment-date">
                        ${comment.created}
                    </span>
                </div>

                <p class="comment-content">
                    ${comment.content}
                </p>
            </li>
        `);
    });
};

let submitComment = () => {
    const boardId = $('#hiddenId').val();
    const content = $('#commentContent').val();

    if (!content || content.trim() === '') {
        alert('댓글 내용을 입력해주세요.');
        return;
    }

    $.ajax({
        type: 'POST',
        url: '/api/boards/' + boardId + '/comments',

        headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },

        contentType: 'application/json',

        data: JSON.stringify({
            content: content.trim()
        }),

        success: () => {
            $('#commentContent').val('');
            loadBoardDetail();
        },

        error: (error) => {
            handleRequestError(
                error,
                '댓글 등록 중 오류가 발생했습니다.'
            );
        }
    });
};

let checkBoardOwner = (boardWriterId) => {
    $.ajax({
        type: 'GET',
        url: '/api/members/info',

        headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`
        },

        success: (member) => {
            if (member.userId === boardWriterId) {
                $('#editBtn').show();
                $('#deleteBtn').show();
                return;
            }

            $('#editBtn').hide();
            $('#deleteBtn').hide();
        },

        error: (error) => {
            handleRequestError(
                error,
                '사용자 정보를 불러오는데 오류가 발생했습니다.'
            );
        }
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
        alert('요청을 수행할 권한이 없습니다.');
        return;
    }

    alert(defaultMessage);
};
