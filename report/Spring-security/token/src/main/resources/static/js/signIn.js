$(document).ready(() => {
    $('#signin').click(() => {
        let formData = {
            userId: $('#user_id').val(),
            password: $('#password').val()
        }

        $.ajax({
            type: 'POST',
            url: '/api/users/login',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function(response) {
                localStorage.setItem('accessToken', response.accessToken);
                alert(response.message);
                window.location.href = response.url;
            },
            error: function(xhr) {
                let response = xhr.responseJSON;
                alert(response && response.message ? response.message : '로그인 중 오류가 발생했습니다.');
            }
        });
    });
});