let setupAjax = () => {
    $.ajaxSetup({
        beforeSend: (xhr) => {
            let token = localStorage.getItem("accessToken");
            if (token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            }
        }
    });
}

let refreshTokens = () => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'POST',
            url: '/api/tokens/refresh',
            dataType: 'json',
            xhrFields: {
                withCredentials: true
            },
            success: (response) => {
                localStorage.setItem('accessToken', response.accessToken);
                resolve(response);
            },
            error: (xhr) => reject(xhr)
        });
    });
};

let getUserInfo = () => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'GET',
            url: '/api/users/info',
            dataType: 'json',
            success: (response) => resolve(response),
            error: (xhr) => reject(xhr)
        });
    });
}

let redirectToLogin = () => {
    alert('로그인이 필요합니다. 다시 로그인해주세요.');
    localStorage.removeItem('accessToken');
    window.location.href = '/users/login';
}
