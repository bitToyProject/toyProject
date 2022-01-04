import { useInput } from 'src/webapp/hook/useInput';
import { useEffect, useMemo } from 'react';
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
const InputModule = (input: PropTypes['input']) => {
  const inputValue = useInput('');

  useEffect(() => {
    input.onChange(inputValue.value);
  }, [inputValue.value]);

  return (
    <>
      <input
        className="py-2 px-3 border border-gray-300 focus:border-red-300 focus:outline-none focus:ring focus:ring-red-200 focus:ring-opacity-50 rounded-md shadow-sm disabled:bg-gray-100 mt-1 block w-full"
        type={input.type}
        disabled={input.disabled}
        placeholder={input.placeholder}
        {...inputValue}
      />
    </>
  );
};

export default InputModule;
