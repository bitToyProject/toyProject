import axios from "axios";
import { useMemo } from "react";
interface IPostAxiosProps {}

export const useAsyncService = async (
  method: string,
  url: string,
  data: object | Array<any> | null
) => {
  const postAxios = axios({
    url: url,
    method: `POST`,
    data: data,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  });
  const getAxios = axios({
    url: url,
    method: `GET`,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  });
  return method === "get" ? getAxios : postAxios;
};
