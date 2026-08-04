$(document).ready(() => {

    $('#signin').click(() => {

        const userId = $('#user_id').val();
        const password = $('#password').val();

        const formData = {
            username: userId,
            password: password
        };

        $.ajax({
            type: 'POST',
            url: '/api/members/login',
            data: JSON.stringify(formData),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',

            success: (response) => {
                saveTokens(response.accessToken, response.refreshToken);

                alert('로그인에 성공했습니다.');

                location.replace('/');
            },

            error: (error) => {
                console.error('로그인 오류:', error);

                const message =
                    error.responseJSON?.message
                    ?? '아이디 또는 비밀번호가 올바르지 않습니다.';

                alert(message);
            }
        });
    });
});
