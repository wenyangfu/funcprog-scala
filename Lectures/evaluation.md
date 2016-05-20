### Notes for lectures 1.2 - Elements of Programming and 1.3 - Evaluation Strategies and Termination

# Evaluation
A non-primitive expression is evaluated as follows:

1. Take leftmost operator
2. Eval its operands (left -> right)
3. Apply the op to the operands

A name is evaluated by replacing it w/ the RHS of its defn. The evaluation process stops once it results in a value - for the moment, a value is just a number.Later on, we'll consider many other types of values.

### Parameters
Defns can also have params. For instance:
```scala
def square(x: Double) = x * x
square: (Double: Double)
```

### Params and Return Types
Function params come w/ their type, which is given after a colon
```scala
def power(x: Double, y: Int)
```

### The substitution model
This scheme of expression evaluation is called the substitution model. The idea behind this model is that all evaluation does is *reduce* an expression to a value. It can be applied to all expressions, as long as they have no side effects. The substitution model is formalized in the lambda-calculus, which gives a foundation for functional programming!

For example, we can't write a simple expression like ```var c = 1; c++```, because post-incrementing ```c``` will introduce side effects.

### Termination
- Does every expression reduce to a value (in a finite # of steps)?
- Nope!
	- Counterexample:
```scala
def loop: Int = loop
// loop-> loop -> reduces ad infinitum...
``` 
### Changing the evaluation strategy
The interpreter reduces function arguments to values before rewriting the function application. One could alternatively apply the function to unreduced arguments. For instance:
```scala
sumOfSquares(3, 2+2)
// Don't reduce to 4, simply pass 2+2 to the function.
square(3) + square(2+2)
3*3 + square(2+2)
9 + square(2+2)
// ...
```
### Call-by-name and call-by-value
The first evaluation strategy is known as *call-by-value*, the second is *call-by-name*. Both strategies reduce to the same final values as long as:

- The reduced expressions consists of pure functions, and
- both evaluations terminate.

Call-by-value (CBV) has the advantage that it evaluates every function arg only once.
Call-by-name (CBN) has the advantage that a function arg is not evaluated if the corresponding param is unused in the evaluation of the function body.

### Further comparisons of call by name and call-by-value termination
What if termination is not guaranteed? We have:
- If CBV evaluation of an expression *e* terminates, then CBN evaluation of *e* terminates, too.
- The other direction is *not true*!!!

Non-termination example - let's define:
```scala
def first(x: Int, y: Int) = x
```
and consider the expr ```first(1, loop)```.
Under CBN, value is 1. Under CBV, loop will be evaluated infinitely and fail.

### Scala's evaluation strategy
Scala normally uses call-by-value. But if the type of a function parameter starts with => it uses call-by-name.
For example, we can force call-by-name by doing the following:
```scala
def constOne(X: Int, y: => Int) = 1
```
Then, x is CBV and y is CBN. Let's try to trace the evaluation paths of the following function calls:
```scala
constOne(1+2,loop)
x = 1+2
constOne(3,loop)
// y is unused, so discard.
// done
constOne(loop,1+2)
x = loop
// infinite loop, unable to resolve
