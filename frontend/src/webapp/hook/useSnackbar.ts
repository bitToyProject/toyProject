import { useEffect, useState } from "react"

export const useSnackbar = () => {
  const [msg, setMessage] = useState<string>("");
  const [action, setAction] = useState<boolean>(false);
  const [show, setShow] = useState<boolean>(false);

  useEffect(() => {
    setTimeout(() => {
      setShow(false)
    }, 4000)
  }, [show])

  const openSnackbar = (active:boolean, msg:string) => {
    setMessage(msg);
    setAction(active);
    setShow(true);
  }

  return { action, msg, show, openSnackbar}
}