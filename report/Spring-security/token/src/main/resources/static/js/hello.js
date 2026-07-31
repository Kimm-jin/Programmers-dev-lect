$(document).ready(() => {
    setupAjax();
    loadUserInfo();

    $('#logout').click(() => logout());
    $('#call-user').click(() => callAuthorityApi('/api/users/user'));
    $('#call-admin').click(() => callAuthorityApi('/api/users/admin'));
});


let callAuthorityApi = (url) => {
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        success: (response) => {
            $('#authority-result').text(response.message).css('color', 'green');
        },
        error: (xhr) => {
            let response = xhr.responseJSON;
            let message = response && response.message ? response.message : '요청에 실패했습니다.';
            $('#authority-result').text(message).css('color', 'red');
        }
    });
}


let logout = () => {
    $.ajax({
        type: 'POST',
        url: '/api/users/logout',
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: (response) => {
            localStorage.removeItem('accessToken');
            alert(response.message);
            window.location.href = response.url;
        },
        error: () => {

            localStorage.removeItem('accessToken');
            window.location.href = '/users/login';
        }
    });
}

let loadUserInfo = async () => {
    try {
        if (localStorage.getItem('accessToken') == null) {
            await refreshTokens();
        }
        renderUserInfo(await getUserInfo());
    } catch (e) {
        try {
            await refreshTokens();
            renderUserInfo(await getUserInfo());
        } catch (e2) {
            redirectToLogin();
        }
    }
}

let renderUserInfo = (user) => {
    $('#user-name').text(user.userName);
    $('#user-id').text(user.userId);
    $('#user-role').text(user.role);


    if (user.role === 'ROLE_ADMIN') {
        $('#admin-link').show();
    }
}
