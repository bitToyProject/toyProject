import { useInput } from "@/webapp/hook/useInput";
import { css } from "@emotion/react";

import React, { useEffect, useMemo } from "react";
import { COLOR_BLACK } from "../CCstyle/CCstyle";
interface PropTypes {
  input: {
    type: string;
    disabled: boolean;
    placeholder: string;
    // onFocus: () => void;
    // onBlur: () => void;
    onChange: (value: any) => void;
  };
}
const InputModule = (input: PropTypes["input"]) => {
  const inputValue = useInput("");

  useEffect(() => {
    input.onChange(inputValue.value);
  }, [inputValue.value]);

  return (
    <>
      <input
        type={input.type}
        disabled={input.disabled}
        placeholder={input.placeholder}
        {...inputValue}
      />
    </>
  );
};

export default InputModule;

const cssInput = css`
  width: 100%;
  height: 100%;
  border: 0.5rem solid ${COLOR_BLACK};
`;
