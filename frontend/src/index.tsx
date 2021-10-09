import React, { Suspense } from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <RecoilRoot>
                <Suspense fallback={<div>Loading...</div>}>
                    <App />
                </Suspense>
            </RecoilRoot>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);
