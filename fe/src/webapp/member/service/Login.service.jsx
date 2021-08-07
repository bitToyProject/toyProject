import axios from 'axios';

const apiLogin  = (data) =>{
    return axios({
        url: "/login",
        method: 'POST',
        data: data,
        headers:{
            "Content-Type": "application/json; charset=utf-8",
        }
    });
};

export default {apiLogin}