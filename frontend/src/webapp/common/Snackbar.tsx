/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import {
  COLOR_SUCCESS,
  COLOR_FAIL,
  COLOR_WHITE,
  COLOR_GRAY,
} from '@/webapp/common/CCstyle/CCstyle';

interface PropTypes {
  snackbar: {
    action: boolean;
    msg: string;
    show: boolean;
  };
}

const Snackbar = (snackbar: PropTypes['snackbar']) => {
  return (
    <>
      <div
        css={css`
          boarder: 1px solid;
          font-size: 18px;
          position: fixed;
          z-index: 10;
          top: 5%;
          right: 1%;
          heigth: 4rem;
          weigth: 39rem;
          padding: 1rem;
          float: right;
          border-radius: 1rem;
          box-shadow: 0.5px 0.5px 0.5px 0.5px ${COLOR_GRAY};
          color: ${COLOR_WHITE};
          visibility: ${snackbar.show ? `visible` : `hidden`};
          background-color: ${snackbar.action
            ? `${COLOR_SUCCESS}`
            : `${COLOR_FAIL}`};
        `}
      >
        {snackbar.msg}
      </div>
    </>
  );
};

export default Snackbar;
