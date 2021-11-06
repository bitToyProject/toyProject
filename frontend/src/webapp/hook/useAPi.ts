import axios from "axios";
import React, { useCallback, useMemo, useState } from "react";
import { useSetRecoilState } from "recoil";

interface IApiType {
  data: object;
  status: number;
  loading: boolean;
  error: string;
}
export const useApi = async (url: string, postData: object) => {
  const [data, setData] = useState<object>({});
  const [status, setStatus] = useState<any | null>();
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  if (!url && !postData) return;
  setData({});
  setStatus(null);
  setError("");
  setLoading(true);
  try {
    const response = await axios({
      url: url,
      data: postData,
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
    });
    return [setData(response.data), setStatus(response.status), setError("")];
  } catch (e) {
    return [setData({}), setStatus(0), setLoading(false), setError("")];
  }
};
