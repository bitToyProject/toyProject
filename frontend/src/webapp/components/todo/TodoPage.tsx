import { InputModule } from 'src/webapp/common';
import { BOX_SHADOW } from 'src/webapp/common/CCstyle/CCstyle';
/** srcjsxImportSource srcemotion/react */
import { css } from '@emotion/react';

const TodoPage = () => {
  return (
    <>
      <h1>todo page</h1>
      <div
        css={css`
          max-width: 40rem;
          width: 100%;
          margin: auto;
          background-color: #fff;
          border-radius: 1rem;
          font-size: 18px;
          overflow: hidden;
          box-shadow: ${BOX_SHADOW};
        `}
      >
        <InputModule
          type={'nickName'}
          disabled={false}
          placeholder={'✍️ Add a new task'}
          onChange={(value: string) => console.log('test')}
        />
      </div>
    </>
  );
};

export default TodoPage;
