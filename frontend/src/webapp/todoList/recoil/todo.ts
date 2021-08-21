import {atom} from 'recoil';

export interface ITodoType {
    id : number,
    contents : string,
    isCompleted : boolean
}

export const inputState = atom<string>({
    key : 'inputState',
    default : ''
})

export const todoState = atom<ITodoType[]>({
    key: 'todos',
    default: [],
})