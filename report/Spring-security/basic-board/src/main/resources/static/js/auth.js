const ACCESS_TOKEN_KEY = 'accessToken';
const REFRESH_TOKEN_KEY = 'refreshToken';
const LOGIN_URL = '/members/login';

let refreshRequest = null;

const getAccessToken = () => localStorage.getItem(ACCESS_TOKEN_KEY);
const getRefreshToken = () => localStorage.getItem(REFRESH_TOKEN_KEY);

const saveTokens = (accessToken, refreshToken) => {
    localStorage.setItem(ACCESS_TOKEN_KEY, accessToken);

    if (refreshToken) {
        localStorage.setItem(REFRESH_TOKEN_KEY, refreshToken);
    }
};

const clearTokens = () => {
    localStorage.removeItem(ACCESS_TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
};

const moveToLogin = () => {
    clearTokens();
    window.location.href = LOGIN_URL;
};

const checkToken = () => {
    if (!getAccessToken() && !getRefreshToken()) {
        moveToLogin();
        return false;
    }

    return true;
};

const refreshAccessToken = () => {
    if (refreshRequest) {
        return refreshRequest;
    }

    const refreshToken = getRefreshToken();

    if (!refreshToken) {
        return $.Deferred().reject({status: 401}).promise();
    }

    refreshRequest = $.ajax({
        type: 'POST',
        url: '/api/tokens/refresh',
        contentType: 'application/json',
        data: JSON.stringify({refreshToken})
    }).done((response) => {
        saveTokens(response.accessToken);
    }).always(() => {
        refreshRequest = null;
    });

    return refreshRequest;
};

const authAjax = (options) => {
    const send = (retried) => {
        const accessToken = getAccessToken();
        const headers = {...(options.headers || {})};

        if (accessToken) {
            headers.Authorization = `Bearer ${accessToken}`;
        }

        const requestOptions = {
            ...options,
            headers
        };

        const originalError = options.error;

        requestOptions.error = (xhr, textStatus, errorThrown) => {
            if (xhr.status === 401 && !retried) {
                refreshAccessToken()
                    .done(() => send(true))
                    .fail(() => moveToLogin());
                return;
            }

            if (xhr.status === 401) {
                moveToLogin();
                return;
            }

            if (originalError) {
                originalError(xhr, textStatus, errorThrown);
            }
        };

        return $.ajax(requestOptions);
    };

    return send(false);
};

const handleRequestError = (error, defaultMessage) => {
    console.error('요청 오류:', error);

    if (error.status === 403) {
        alert('요청을 수행할 권한이 없습니다.');
        return;
    }

    alert(defaultMessage);
};

function logout() {
    clearTokens();
    alert('로그아웃되었습니다.');
    window.location.href = LOGIN_URL;
}
