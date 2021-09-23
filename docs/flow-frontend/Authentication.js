var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const $wnd = window;
/**
 * A helper method for Spring Security based form login.
 * @param username
 * @param password
 * @param options defines additional options, e.g, the loginProcessingUrl, failureUrl, defaultSuccessUrl etc.
 */
export function login(username, password, options) {
    return __awaiter(this, void 0, void 0, function* () {
        let result;
        try {
            const data = new FormData();
            data.append('username', username);
            data.append('password', password);
            const loginProcessingUrl = options && options.loginProcessingUrl ? options.loginProcessingUrl : '/login';
            const headers = getSpringCsrfTokenHeadersFromDocument(document);
            const response = yield fetch(loginProcessingUrl, { method: 'POST', body: data, headers });
            const failureUrl = options && options.failureUrl ? options.failureUrl : '/login?error';
            // this assumes the default Spring Security form login configuration (handler URL and responses)
            if (response.ok && response.redirected) {
                if (response.url.endsWith(failureUrl)) {
                    result = {
                        error: true,
                        errorTitle: 'Incorrect username or password.',
                        errorMessage: 'Check that you have entered the correct username and password and try again.'
                    };
                }
                else {
                    const vaadinCsrfToken = yield updateCsrfTokensBasedOnResponse(response);
                    if (vaadinCsrfToken) {
                        result = {
                            error: false,
                            token: vaadinCsrfToken,
                            redirectUrl: response.url
                        };
                    }
                }
            }
        }
        catch (e) {
            result = {
                error: true,
                errorTitle: e.name,
                errorMessage: e.message
            };
        }
        return (result || {
            error: true,
            errorTitle: 'Error',
            errorMessage: 'Something went wrong when trying to login.'
        });
    });
}
/**
 * A helper method for Spring Security based form logout
 * @param options defines additional options, e.g, the logoutUrl.
 */
export function logout(options) {
    var _a, _b;
    return __awaiter(this, void 0, void 0, function* () {
        // this assumes the default Spring Security logout configuration (handler URL)
        const logoutUrl = options && options.logoutUrl ? options.logoutUrl : '/logout';
        try {
            const headers = getSpringCsrfTokenHeadersFromDocument(document);
            yield doLogout(logoutUrl, headers);
        }
        catch (_c) {
            try {
                const response = yield fetch('?nocache');
                const responseText = yield response.text();
                const doc = new DOMParser().parseFromString(responseText, 'text/html');
                const headers = getSpringCsrfTokenHeadersFromDocument(doc);
                yield doLogout(logoutUrl, headers);
            }
            catch (error) {
                // clear the token if the call fails
                (_b = (_a = $wnd.Vaadin) === null || _a === void 0 ? void 0 : _a.TypeScript) === null || _b === void 0 ? true : delete _b.csrfToken;
                clearSpringCsrfMetaTags();
                throw error;
            }
        }
    });
}
function doLogout(logoutUrl, headers) {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch(logoutUrl, { method: 'POST', headers });
        if (!response.ok) {
            throw new Error(`failed to logout with response ${response.status}`);
        }
        yield updateCsrfTokensBasedOnResponse(response);
    });
}
function updateSpringCsrfMetaTag(body) {
    const doc = new DOMParser().parseFromString(body, 'text/html');
    clearSpringCsrfMetaTags();
    Array.from(doc.head.querySelectorAll('meta[name="_csrf"], meta[name="_csrf_header"]')).forEach((el) => document.head.appendChild(document.importNode(el, true)));
}
function clearSpringCsrfMetaTags() {
    Array.from(document.head.querySelectorAll('meta[name="_csrf"], meta[name="_csrf_header"]')).forEach((el) => el.remove());
}
const getCsrfTokenFromResponseBody = (body) => {
    const match = body.match(/window\.Vaadin = \{TypeScript: \{"csrfToken":"([0-9a-zA-Z\\-]{36})"}};/i);
    return match ? match[1] : undefined;
};
const getSpringCsrfTokenHeadersFromDocument = (doc) => {
    const csrf = doc.head.querySelector('meta[name="_csrf"]');
    const csrfHeader = doc.head.querySelector('meta[name="_csrf_header"]');
    const headers = {};
    if (csrf !== null && csrfHeader !== null) {
        headers[csrfHeader.content] = csrf.content;
    }
    return headers;
};
function updateCsrfTokensBasedOnResponse(response) {
    return __awaiter(this, void 0, void 0, function* () {
        const responseText = yield response.text();
        const token = getCsrfTokenFromResponseBody(responseText);
        $wnd.Vaadin.TypeScript = $wnd.Vaadin.TypeScript || {};
        $wnd.Vaadin.TypeScript.csrfToken = token;
        updateSpringCsrfMetaTag(responseText);
        return token;
    });
}
/**
 * A helper class for handling invalid sessions during an endpoint call.
 * E.g., you can use this to show user a login page when the session has expired.
 */
export class InvalidSessionMiddleware {
    constructor(onInvalidSessionCallback) {
        this.onInvalidSessionCallback = onInvalidSessionCallback;
    }
    invoke(context, next) {
        return __awaiter(this, void 0, void 0, function* () {
            const clonedContext = Object.assign({}, context);
            clonedContext.request = context.request.clone();
            const response = yield next(context);
            if (response.status === 401) {
                const loginResult = yield this.onInvalidSessionCallback();
                if (loginResult.token) {
                    clonedContext.request.headers.set('X-CSRF-Token', loginResult.token);
                    return next(clonedContext);
                }
            }
            return response;
        });
    }
}
//# sourceMappingURL=Authentication.js.map