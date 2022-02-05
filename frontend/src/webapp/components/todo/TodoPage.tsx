import { InputModule } from "src/webapp/common";

const TodoPage = () => {
  return (
    <>
      <h1>todo page</h1>
      <div className="">
        <div className="flex w-96">
          <InputModule
            type={"nickName"}
            disabled={false}
            placeholder={"✍️ Add a new task"}
            onChange={(value: string) => console.log("test")}
          />
          <button className="border border-gray-900 rounded-md w-32">
            추가하기
          </button>
        </div>
      </div>
    </>
  );
};

export default TodoPage;
