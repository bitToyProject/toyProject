export const useFileUpload = (file:any) => {
    console.log(typeof file);
    console.log(file);

    const data = new FormData();

    data.append("file", file);

    console.log(data);
    return {data};
}