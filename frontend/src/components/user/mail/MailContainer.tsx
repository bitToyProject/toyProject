import React, { useCallback, useState } from 'react';
import InputModule from 'src/components/common/input/InputModule';

const MailPage = () => {
    const [mailCode, setMailCode] = useState<string>("");

    return (
        <div className='flex items-center justify-center w-full h-screen'>
            <div className=''>
                <InputModule
                    type={"text"}
                    disabled={false}
                    placeholder={"전송된 코드를 입력해주세요."}
                    onChange={(value: string) => setMailCode(value)}
                />
            </div>
        </div>
    );
}

export default MailPage;
