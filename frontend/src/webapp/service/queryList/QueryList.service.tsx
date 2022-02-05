import axios, { AxiosResponse } from 'axios';
import { useMutation } from 'react-query';

const QueryListService = () => {
  const createSimpleMutation = useMutation((data: string) => {
    return axios.post('url', data);
  });

  const createMutaion = useMutation<
    AxiosResponse<any>,
    unknown,
    string,
    unknown
  >((data: string) => axios.post('url', { text: data }), {
    onMutate: async (data: string) => {
      // mutate가 call 되는 경우
      // Optionally return a context containing data to use when for example rolling back
      return { id: 1 };
    },
    onError: async (err, variables, context) => {
      // 에러인 경우
    },
    onSuccess: async (data, variables, context) => {
      // 성공
    },
    onSettled: async (data, error, variables, context) => {
      // mutate, error가 fetch된 이후 항상 실행
    },
  });
};

export default QueryListService;
