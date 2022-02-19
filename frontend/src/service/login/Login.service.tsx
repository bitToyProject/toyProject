import axios from "axios";

export const apiPost = (url: string, data: any) => {
  console.log("data", data);

  return axios({
    url: url,
    method: `POST`,
    data: data,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
    },
  });
};
