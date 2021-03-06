export interface ISignupType {
  [key: string]: any;
  username: string;
  password: string;
  nickName: string;
  phoneNum: string;
  firstName: string;
  lastName: string;
  gender: number;
}

export interface ISignupValType {
  username: string;
  password: string;
  nickName: string;
  phoneNum: string;
  firstName: string;
  lastName: string;
  gender: number;
}

export interface IResStatus {
  result: string;
}

// export interface Param extends ISignupType {
//   [key: string]: any;
// }