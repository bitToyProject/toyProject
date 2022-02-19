import { AxiosError, AxiosRequestConfig } from "axios";
import { useCallback, useReducer } from "react";
import { useQueryClient } from "react-query";

type StateType = {
  data: any;
  loading: boolean;
  error: AxiosError | null;
};

type ActionType = {
  type: string;
  data?: any;
  error?: AxiosError | any;
};

const useReactQuery = (url: string, data: object) => {
  const queryClient = useQueryClient();
};
export default useReactQuery;
