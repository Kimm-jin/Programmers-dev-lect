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

    authAjax({
        type: 'DELETE',
        url: '/api/boards/' + resourceId,

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

let loadBoardDetail = () => {
    const boardId = $('#hiddenId').val();

    authAjax({
        type: 'GET',
        url: '/api/boards/' + boardId + '/with-comments',

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

    authAjax({
        type: 'POST',
        url: '/api/boards/' + boardId + '/comments',

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
    authAjax({
        type: 'GET',
        url: '/api/members/info',

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

