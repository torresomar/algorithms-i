###### Quick-Find

Cost-Model 

|Algorithm    |Initialize   |Union    |Find     
|:-----------:|:-----------:|:-------:|:--:
|Quick-Find   |N            |N        |1

The problem with QuickFind is that Union is way expensive.

If we would like to make a N union on N objects it would be N².
N² algorithms for large problems are not acceptable.
They do not scale!!!

###### Quick-Union

It is a lazy approach
- Integer arra id[] of size N
- Interpretation : id[i] is parent of i
- Root of i is id[id[id....[id[i]....]]]

|Algorithm    |Initialize   |Union    |Find     
|:-----------:|:-----------:|:-------:|:--:
|Quick-Union  |N            |N        |N

The problem with quick union is that trees can reach a very tall height.
Find may be very expensive.

###### Quick-Union Improvements

Weighted quick-union
- Avoid tall trees
- Keep track of the size of each tree
- Balance by linking root of smaller tree to root of large one
