$(document).ready(() => {
    if (!checkToken()) {
        return;
    }

    loadMemberInfo();
    loadBoard(1);

    $('#searchBtn').on('click', () => {
        loadBoard(1);
    });

    $('#searchResetBtn').on('click', () => {
        $('#searchTitle').val('');
        $('#searchUserId').val('');
        $('#searchFrom').val('');
        $('#searchTo').val('');

        loadBoard(1);
    });

    $('#searchTitle, #searchUserId').on('keydown', (event) => {
        if (event.key === 'Enter') {
            loadBoard(1);
        }
    });
});

const PAGE_SIZE = 10;

let loadMemberInfo = () => {
    authAjax({
        type: 'GET',
        url: '/api/members/info',

        success: (member) => {
            $('#loginUserName').text(member.userName);
        },

        error: (error) => {
            handleRequestError(
                error,
                '사용자 정보를 불러오는데 오류가 발생했습니다.'
            );
        }
    });
};

let getSearchCondition = () => {
    const condition = {};

    const title = $('#searchTitle').val();
    const userId = $('#searchUserId').val();
    const from = $('#searchFrom').val();
    const to = $('#searchTo').val();

    if (title) {
        condition.title = title;
    }

    if (userId) {
        condition.userId = userId;
    }

    if (from) {
        condition.from = from;
    }

    if (to) {
        condition.to = to;
    }

    return condition;
};

let loadBoard = (page) => {
    authAjax({
        type: 'GET',
        url: '/api/boards/search',

        data: {
            page: page,
            size: PAGE_SIZE,
            ...getSearchCondition()
        },

        success: (response) => {
            renderBoards(response.content);
            renderPagination(page, response.totalPages);
        },

        error: (error) => {
            handleRequestError(
                error,
                '게시판 데이터를 불러오는데 오류가 발생했습니다.'
            );
        }
    });
};

let renderBoards = (boards) => {
    const $content = $('#boardContent');

    $content.empty();

    if (!boards || boards.length === 0) {
        $content.append(`
            <tr>
                <td colspan="5" style="text-align: center;">
                    글이 존재하지 않습니다.
                </td>
            </tr>
        `);

        return;
    }

    boards.forEach((item) => {
        const author = item.userName
            ? `${item.userName} (${item.userId})`
            : item.userId;

        const commentBadge = item.commentCount > 0
            ? `<span class="comment-count">${item.commentCount}</span>`
            : '-';

        $content.append(`
            <tr>
                <td>${item.id}</td>

                <td>
                    <a href="/detail?id=${item.id}">
                        ${item.title}
                    </a>
                </td>

                <td>${author}</td>
                <td>${commentBadge}</td>
                <td>${item.created}</td>
            </tr>
        `);
    });
};

let renderPagination = (currentPage, totalPages) => {
    const $pagination = $('#pagination');

    $pagination.empty();

    for (let page = 1; page <= totalPages; page++) {
        const $button = $(`
            <button class="btn page-btn">
                ${page}
            </button>
        `);

        if (page === currentPage) {
            $button.addClass('active');
            $button.prop('disabled', true);
        }

        $button.on('click', () => {
            loadBoard(page);
        });

        $pagination.append($button);
    }
};

