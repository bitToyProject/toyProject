export interface ISignupType {
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

export interface Param extends ISignupType {
  [key: string]: any;
}

export interface ToggleButton {
  [BOLD: string]: boolean;
  ITALIC: boolean;
  UNDERLINE: boolean;
  STRIKETHROUGH: boolean;
};
