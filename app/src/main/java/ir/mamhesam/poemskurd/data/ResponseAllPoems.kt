package ir.mamhesam.poemskurd.data

import com.google.gson.annotations.SerializedName

data class ResponseAllPoems(

	@field:SerializedName("year")
	val year: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String
)
