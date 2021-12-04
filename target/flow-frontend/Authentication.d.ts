import { MiddlewareClass, MiddlewareContext, MiddlewareNext } from './Connect';
export interface LoginResult {
    error: boolean;
    token?: string;
    errorTitle?: string;
    errorMessage?: string;
    redirectUrl?: string;
    defaultUrl?: string;
}
export interface LoginOptions {
    loginProcessingUrl?: string;
}
export interface LogoutOptions {
    logoutUrl?: string;
}
/**
 * A helper method for Spring Security based form login.
 * @param username
 * @param password
 * @param options defines additional options, e.g, the loginProcessingUrl etc.
 */
export declare function login(username: string, password: string, options?: LoginOptions): Promise<LoginResult>;
/**
 * A helper method for Spring Security based form logout
 * @param options defines additional options, e.g, the logoutUrl.
 */
export declare function logout(options?: LogoutOptions): Promise<void>;
/**
 * It defines what to do when it detects a session is invalid. E.g.,
 * show a login view.
 * It takes an <code>EndpointCallContinue</code> parameter, which can be
 * used to continue the endpoint call.
 */
export declare type OnInvalidSessionCallback = () => Promise<LoginResult>;
/**
 * A helper class for handling invalid sessions during an endpoint call.
 * E.g., you can use this to show user a login page when the session has expired.
 */
export declare class InvalidSessionMiddleware implements MiddlewareClass {
    private onInvalidSessionCallback;
    constructor(onInvalidSessionCallback: OnInvalidSessionCallback);
    invoke(context: MiddlewareContext, next: MiddlewareNext): Promise<Response>;
}
