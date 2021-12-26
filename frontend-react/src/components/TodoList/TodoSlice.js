
const initState = [
    
];

const todoListReducer = (state = initState, action) => {
    switch (action.type) {
        case 'todoList/addTodo':
            return [...state, action.payload];
        case 'todoList/addListTodo':
            return [...action.payload];
        case 'todoList/updateTodo':
            return state.map((todo) => {
                if (todo.id === action.payload.id) {
                    return {
                        ...todo,
                        name: action.payload.name
                    }
                }
                return todo;
            });
        case 'todoList/toggleTodoStatus':
            return state.map(todo => todo.id === action.payload ? {...todo, completed: !todo.completed}
                : todo);
        case 'todoList/deleteTodo':
            var array = [...state];
            var index = array.findIndex(obj => obj.id === action.payload);
            if (index !== -1) {
              array.splice(index, 1);
              return array;
            }
            return [...state];
        default:
            return state;
    }
}

export default todoListReducer;
