import { AxiosError, AxiosRequestConfig } from "axios";
import { useCallback, useReducer } from "react";

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

const reducer = (state: StateType, action: ActionType) => {
  switch (action.type) {
    case "LOADING":
      return {
        data: null,
        loading: true,
        error: null,
      };
    case "SUCCESS":
      return {
        data: action.data as any,
        loading: false,
        error: null,
      };
    case "ERROR":
      return {
        data: null,
        loading: false,
        error: action.error as AxiosError,
      };
    default:
      throw new Error(`Unhandled action type: ${action.type}`);
  }
};

export type AsyncFc<TResult> = (
  [...arg]: any[],
  config: AxiosRequestConfig
) => Promise<TResult>;

const useAsync = <TResult>(
  callback: AsyncFc<TResult>,
  config: AxiosRequestConfig = {}
) => {
  const [state, dispatch] = useReducer(reducer, {
    data: null,
    loading: false,
    error: null,
  });

  const request = useCallback(
    async (...args) => {
      dispatch({ type: "LOADING" });
      try {
        const data = await callback([...args], config);
        dispatch({ type: "SUCCESS", data });
        return data;
      } catch (error) {
        dispatch({ type: "ERROR", error });
      }
    },
    [callback, config]
  );

  return { ...state, request };
};

export default useAsync;
