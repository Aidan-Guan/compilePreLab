define dso_local i32 @main(){
    %1 = alloca i32
    %2 = sub i32 123, 122
    store i32 %2, i32* %1
    %3 = load i32, i32* %1
    ret i32 %3
}