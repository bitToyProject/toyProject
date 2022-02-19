export const checkNull = (item: Array<any>) => {
  const nullExp = (item: any) => {
    return (
      item === "" ||
      item === undefined ||
      (item !== null && item === "object" && !Object.keys(item).length) ||
      item.length === 0
    );
  };
  return item.some(nullExp) ? true : false;
};

/*
validateEmail.jsx Jinwook oh 2021.8.12

이메일 / 비밀번호 / 생년월일  / 핸드폰 번호 / 인증번호 체크하는 정규 표현식 모듈

*/

export const validateEmail = (email: string) => {
  console.log("email,", email);
  const emailRegExp =
    /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  // 형식에 맞는 경우 true 리턴
  if (email?.length > 0) {
    if (emailRegExp.test(email)) {
      return { isError: false, msg: "이메일 형식이 일치합니다." };
    } else {
      return { isError: true, msg: "이메일이 올바르지 않습니다." };
    }
  } else {
    return { isError: false, msg: "" };
  }
};

export const validatePassword = (password: string) => {
  console.log("password validation 1 : ", password);

  //  8 ~ 10자 영문, 숫자 조합
  const pwRegExp =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
  if (password?.length > 0) {
    if (pwRegExp.test(password)) {
      return { isPwError: false, pwMsg: "잘 작성하셨어요" };
    } else {
      return {
        isPwError: true,
        pwMsg: "8 ~ 16자 영문, 숫자, 특수문자 조합으로 작성해주세요",
      };
    }
  } else {
    return { isPwError: false, pwMsg: "" };
  }
};

export const validateRePassword = (password: string, rePassword: string) => {
  console.log("rePasswordrePasswordrePassword", password, rePassword);
  if (rePassword?.length > 0) {
    if (password === rePassword) {
      return { isReError: false, reReMsg: "비밀번호가 일치합니다." };
    } else {
      return { isReError: true, reReMsg: "비밀번호가 일치하지 않습니다." };
    }
  } else {
    return { isReError: false, reReMsg: "" };
  }
};

export const validateBirthday = (birthday: string) => {
  const birthdayRegex =
    /^(19[0-9][0-9]|20[0-9][0-9])[-/.](0[0-9]|1[0-2])[-/.](0[1-9]|[1-2][0-9]|3[0-1])$/;

  //생일 체크
  if (birthday?.length > 0) {
    if (birthdayRegex.test(birthday)) {
      return { isError: false, msg: "생일 형식이 맞습니다." };
    } else {
      return { isError: true, msg: "생년월일이 올바르지 않습니다" };
    }
  } else {
    return { isError: false, msg: "" };
  }
};

export const validateNumber = (phone: string) => {
  console.log("phone", phone);
  const numRegExp = /^01(?:0|1|[6-9])\d{4}\d{4}$/;
  if (phone?.length > 0) {
    if (numRegExp.test(phone)) {
      return { isNumberError: false, numberMsg: "" };
    } else {
      return {
        isNumberError: true,
        numberMsg: "휴대폰 번호 형식이 잘못되었습니다.",
      };
    }
  } else {
    return { isNumberError: false, numberMsg: "" };
  }
};

export const validateAuthNum = (authnum: string) => {
  const authNumRegExp = /^[0-9]{6}$/;
  if (authnum?.length > 0) {
    if (authNumRegExp.test(authnum)) {
      return { isAuthNumError: false, AuthNumMsg: "" };
    } else {
      return {
        isAuthNumError: true,
        AuthNumMsg: "인증번호는 여섯자리 숫자입니다.",
      };
    }
  } else {
    return { isAuthNumError: false, AuthNumMsg: "" };
  }
};

export const preventSpaceValue = (value: string) => {
  if (value) {
    return value.replace(/\s/gi, "");
  }
};

export const checkPrefixFileName = (value: string) => {
  const cuttingPrefixRegExp = ",";
  return value.substring(value.indexOf(cuttingPrefixRegExp) + 1);
};

// export const pushPrefixFileName = (value:string) => {
//   if (value.type === 0) {
//     const imgPrefixRegExp = "";

//     if (
//       value.src_media.indexOf(".mp4") !== -1 ||
//       value.src_media.indexOf(".mpg") !== -1 ||
//       value.src_media.indexOf(".mpeg") !== -1 ||
//       value.src_media.indexOf(".avi") !== -1
//     ) {
//       return {
//         ...value,
//         src_media: value.src_media,
//         type: "uploadVideo",
//         id: value.id,
//       };
//     } else {
//       return {
//         ...value,
//         src_media: value.src_media,
//         type: "uploadImage",
//         id: value.id,
//       };
//     }
//   } else {
//     return {
//       ...value,
//       type: "youtube",
//       id: value.id,
//     };
//   }
// };
