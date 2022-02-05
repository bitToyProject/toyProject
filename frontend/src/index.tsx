import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import axios from 'axios';
import './index.css';
import 'src/tailwind.output.css';
import { QueryClient, QueryClientProvider } from 'react-query';
axios.defaults.baseURL = 'http://192.168.0.8:8080';

const queryClient = new QueryClient();

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <QueryClientProvider client={queryClient}>
        <RecoilRoot>
          <App />
        </RecoilRoot>
      </QueryClientProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
