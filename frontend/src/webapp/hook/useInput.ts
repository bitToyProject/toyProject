import React, { useState } from "react";

export const useInput = (
  initialState: string,
  validator?: (value: string) => boolean
) => {
  const [value, setValue] = useState(initialState);
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {
      target: { value },
    } = event;
    if (validator === undefined) {
      setValue(value);
    } else {
      const willdata = validator(value);
      if (willdata) {
        setValue(value);
      }
    }
  };
  return { value, onChange };
};
