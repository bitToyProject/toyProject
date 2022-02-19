import Cookies from "universal-cookie";

const cookies = new Cookies();

export const setCookies = (name: string, value: any, option: object) => {
  return cookies.set(name, value, { ...option });
};

export const getCookies = (name: string) => {
  return cookies.get(name);
};

export const removeCookies = (name: string) => {
  return cookies.remove(name);
};
