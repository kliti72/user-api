export const API: string = "http://localhost:8080";

export const environment = {
    PROJECT: {
        NAME: "Nome applicazione",
        VERSION: "1.0.0",
        DESCRIPTION: "Descrizione",
    },
    oAUTH: {
        GOOGLE: {
            oAUTH_URL: "https://accounts.google.com/o/oauth2/v2/auth",
            CLIENT_ID: "138654465599-2ii8lam4iqp1mdj0je7sq0kdtcn11680.apps.googleusercontent.com",
            REDIRECT_URI: "http://localhost:4200/callback",
            SCOPE: "email profile",
            RESPONSE_TYPE: "code",
            INCLUDE_GRANTED_SCOPES: true,
            DECORE: {
                ACCESS_TOKEN: "https://oauth2.googleapis.com/token",
                API: {
                    USER: "https://www.googleapis.com/oauth2/v3/userinfo",
                }
            },
            EXCHANGE_CODE: `${API}/oAuthGoogle/exchange-code/`,
        },
        GITHUB: {
            oAUTH_URL: "https://github.com/login/oauth/authorize",
            CLIENT_ID: "Ov23lijrrKhY7Us1TGrt",
            REDIRECT_URI: "http://localhost:4200/callback",
            SCOPE: "user:email",
            RESPONSE_TYPE: "code",
            INCLUDE_GRANTED_SCOPES: true,
            DECORE: {
                ACCESS_TOKEN: "https://github.com/login/oauth/access_token",
                API: {
                    USER: "https://api.github.com/user",
                }
            },
            EXCHANGE_CODE: `${API}/oAuthGithub/exchange-code/`,},  
    },
    API: {
        AUTHENTICATION: {
            POST_LOGIN: `${API}/auth/login/`,
            POST_REGISTER: `${API}/auth/register/`,
            GET_VALIDATE: `${API}/auth/validate/`,
            GET_CURRENT_USER: `${API}/auth/currunt-user/`,
        },
    },
    DEBUG: {
        ENABLED: true,
        LOG_LEVEL: "verbose",
    },
    TIMEOUTS: {
        API_REQUEST: 5000,
        RETRY_ATTEMPTS: 3,
    },
    I18N: {
        DEFAULT_LANGUAGE: "en",
        SUPPORTED_LANGUAGES: ["en", "es", "fr", "it"],
    },
};
