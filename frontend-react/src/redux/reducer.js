import { combineReducers } from 'redux';
import todoListReducer from '../components/TodoList/TodoSlice';

const rootReducer = combineReducers({
    todoList: todoListReducer,
});

export default rootReducer;
