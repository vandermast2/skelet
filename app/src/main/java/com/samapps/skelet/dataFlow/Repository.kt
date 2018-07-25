package com.samapps.skelet.dataFlow

import com.samapps.skelet.utils.AppContract.DEFAULT_LIMIT
import com.samapps.skelet.utils.AppContract.DEFAULT_OFFSET

interface Repository<Model>{
    companion object {
        val LEFT = "first"
        val RIGHT = "second"
    }

    /**
     * Insert new model [Model] in database
     *
     * @param model - instance of [Model] to insert
     * @return model [Model] updated after inserting in database
     */
    fun insert(model: Model): Model

    /**
     * Insert list of models in database
     *
     * @param models - list [List] of model [Model] instances
     * @return list [List] of model [Model] instances updated after inserting in database
     */
    fun insert(models: List<Model>): List<Model>

    /**
     * Replace model [Model] in database
     *
     * @param model - instance of [Model] to replace
     * @return model [Model] updated after replacing in database
     */
    fun replace(model: Model): Model

    /**
     * Replace list of models in database
     *
     * @param models - list [List] of model [Model] instances
     * @return list [List] of model [Model] instances updated after replacing in database
     */
    fun replace(models: List<Model>): List<Model>

    /**
     * Get model [Model] from database by it's id
     *
     * @param id - value of column id for model
     * @return instance of [Model] or null if there isn't any model with given id
     */
    fun getById(id: Long): Model?

    /**
     * Get models from database
     *
     * @param limit - [Int] limit for models. Method returns no more models than this value
     * @param offset - [Int] shift for database request.
     *
     * @return list [List] of model [Model] instances from database
     */
    fun get(limit: Int = DEFAULT_LIMIT, offset: Int = DEFAULT_OFFSET): List<Model>

    /**
     * Return all models [Model] existed in database
     *
     * @return list [List] of model [Model] instances from database
     */
    fun getAll(): List<Model>

    /**
     * Remove all models [Model] from database
     *
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    fun removeAll(): Int

    /**
     * Returns the numbers of models [Model] in the database.
     *
     * @return the numbers of models [Model] in the database.
     */
    fun count(): Int
}