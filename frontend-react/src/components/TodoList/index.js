import TodoItem from "../TodoItem"
import { todoListSelector } from "../../redux/selectors";
import { useSelector } from 'react-redux';

export default function TodoList() {

    const todoList = useSelector(todoListSelector);

    return (
        <div className="row">
            <h3>TODO</h3>
            <ul>
                {
                    todoList.map(
                        todo => <TodoItem key={todo.id} id={todo.id} name={todo.name} completed={todo.completed} />
                    )
                }
            </ul>
        </div>
    )
}
