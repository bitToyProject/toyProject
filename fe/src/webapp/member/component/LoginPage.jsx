import React from 'react';
import { useState } from 'react';
import { useRecoilState } from 'recoil';
import { loginState } from 'webapp/atom/state';

const LoginPage = () => {

    const [login, setLogin] = useRecoilState(loginState);

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
        setLogin(userInfo);
    }

    return (<>
        <div>
            <div>
                <label>아이디</label>
                <input type="text" name="username" value={userInfo.username} onChange={(e)=>handleChange(e)}></input>
            </div>
            <div>
                <label>비밀번호</label>
                <input type="text" name="password" value={userInfo.password} onChange={(e)=>handleChange(e)}></input>
            </div>
            <div>
                <button onClick={(e)=>handleClick(e)}>로그인</button>
            </div>
        </div> 
    </>);
}

export default LoginPage;