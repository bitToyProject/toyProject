import axios from 'axios';

const apiLogin  = (data) =>{
    return axios({
        url: "http://localhost:8080/login",
        method: 'POST',
        data: data,
        headers:{
            "Content-Type": "application/json; charset=utf-8",
        }
    });
};

export default {apiLogin}