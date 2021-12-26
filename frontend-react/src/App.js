import './App.css';
import TodoList from './components/TodoList';
import AddTodo from './components/AddTodo';
import TopBar from './components/TopBar';

function App() {
  return (
    document.title = 'Todo App',
    <div id='root' className="container">
      <TopBar />
      <TodoList />
      <AddTodo />
    </div>
  );
}

export default App;
