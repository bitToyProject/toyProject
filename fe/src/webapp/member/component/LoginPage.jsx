import React from 'react';
import { useState } from 'react';

const LoginPage = () => {

    const [userInfo, setUserInfo] = useState({
        username : "",
        password : "",
    })

    const handleChange = (e) =>{
        e.preventDefault();
        e.stopPropagation();
        const {name, value} = e.target;
        userInfo[name] = value;
        setUserInfo({...userInfo})

    }

    const handleClick = (e) => {
        e.preventDefault();
        e.stopPropagation();
    }

    return (<>
        <div>
            <div>
                <label>아이디</label>
                <input type="text" onChange={(e)=>handleChange(e)}></input>
            </div>
            <div>
                <label>비밀번호</label>
                <input type="text" onChange={(e)=>handleChange(e)}></input>
            </div>
            <div>
                <button onClick={(e)=>handleClick(e)}>로그인</button>
            </div>
        </div> 
     </>);
}
 
export default LoginPage;