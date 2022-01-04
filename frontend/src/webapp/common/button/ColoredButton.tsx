import { MouseEvent } from 'react';

interface PropTypes {
  btn: {
    disabled: boolean;
    btnLabel: string;
    color: string;
    backgroundColor: string;
    isWhite: boolean;
    handleClick: (e: MouseEvent<HTMLButtonElement | HTMLAnchorElement>) => void;
  };
}
const ColoredButton = (btn: PropTypes['btn']) => {
  return (
    <>
      <button
        className="w-full inline-flex items-center justify-center px-4 py-2 bg-indigo-400 border border-transparent rounded-md font-semibold capitalize text-white hover:bg-indigo-600 active:bg-indigo-600 focus:outline-none focus:bg-indigo-700 focus:ring focus:bg-indigo-200 disabled:opacity-25 transition"
        disabled={btn.disabled}
        onClick={(e) => btn.handleClick(e)}
      >
        {btn.btnLabel}
      </button>
    </>
  );
};

export default ColoredButton;
