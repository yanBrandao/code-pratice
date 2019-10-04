#!/bin/python3

import os
import sys

#
# Complete the contacts function below.
#
def contacts(queries):

    return "lolo"

if __name__ == '__main__':
    fptr = open('ahueha.txt', 'w')

    queries_rows = int(input())

    queries = []

    for _ in range(queries_rows):
        queries.append(input().rstrip().split())

    result = contacts(queries)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
