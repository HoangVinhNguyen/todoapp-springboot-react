export const addTodo = (data) => {
    return {
        type: 'todoList/addTodo',
        payload: data
    }
}
export const addListTodo = (data) => {
    return {
        type: 'todoList/addListTodo',
        payload: data
    }
}
export const updateTodo = (data) => {
    return {
        type: 'todoList/updateTodo',
        payload: data
    }
}
export const deleteTodo = (id) => {
    return {
        type: 'todoList/deleteTodo',
        payload: id
    }
}
export const toggleTodoStatus = (todoId) => {
    return {
        type: 'todoList/toggleTodoStatus',
        payload: todoId
    }
}