import React from "react";

interface ISignInput {
  type?: string;
  text: string;
  setState: React.Dispatch<any>;
}

const EmailInput = ({ type, text, setState }: ISignInput) => {
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setState(e.target.value);
  };
  return (
    <div className="signinform__input">
      <input
        type={type ? type : "text"}
        className="signinform__input__tag"
        onChange={onChange}
        required
      />
      <span className="signinform__input__placeholder">{text}</span>
    </div>
  );
};

export default EmailInput;
