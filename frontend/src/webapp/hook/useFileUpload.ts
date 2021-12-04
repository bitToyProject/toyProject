export const useFileUpload = (file:Blob) => {

    console.log(file);
    const formData = new FormData();

    formData.append("file", file);


    console.log(formData);
   // post하는 api 추가
    return { data: { link: "fsfdsfsf"}};
}