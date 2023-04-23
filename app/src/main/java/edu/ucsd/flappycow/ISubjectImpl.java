interface ISubjectImpl<T> extends ISubject<T> {
    void notify(T data);
}