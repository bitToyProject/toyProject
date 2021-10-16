export interface ISignupType {
    email: string;
    password: string;
}
export interface IResStatus {
    result: string;
}

export interface Param extends ISignupType {
    [key: string]: any;
}