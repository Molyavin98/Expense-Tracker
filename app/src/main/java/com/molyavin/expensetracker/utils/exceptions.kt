package com.molyavin.expensetracker.utils

class IncorrectUserInfoException : Throwable(message = "Incorrect email or password!")
class MissingUserInfoException : Throwable(message = "User mapping has just failed!")
class WhileReceivingDataException : Throwable(message = "Error while receiving data")